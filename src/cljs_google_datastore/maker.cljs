(ns cljs-google-datastore.maker
  (:require [cljs.core :refer [PersistentVector]]
            ["@google-cloud/datastore" :refer [Datastore]]))

(defn- ds-key
  [ds, kind, & {:keys [key]}]
  (.key ds (clj->js (list kind key))))

(defn- ds-filter
  [ds, kind, filter, & {:keys [order, limit, group]}]
  (let [query (.createQuery ds kind)]
    ;; filter loop
    (doseq [[k v] filter]
      (if-not (instance? PersistentVector v)
        (some-> query
                (.filter (name k), (get v 0), (get v 1)))
        (doseq [[sub-v] v]
            (some-> query
                (.filter (name k), (get sub-v 0), (get sub-v 1))))))
    ;; order loop
    (doseq [[k v] order]
      (some-> query (.order (name k) v)))
    ;; limit
    (some-> query (.limit limit))
    ;; group by
    (some-> query (.groupBy group))))

(defn- ds-data [data]
  (map (fn [[k v]] {:name (name k) :value v}) data))