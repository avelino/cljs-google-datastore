(ns cljs-google-datastore.maker
  (:require ["@google-cloud/datastore" :refer [Datastore]]))

(defn- ds-key
  [ds, kind, & {:keys [key]}]
  (.key ds (clj->js (list kind key))))

(defn- ds-filter
  [ds, kind, filter, & {:keys [order, limit]}]
  (let [query (.createQuery ds kind)]
    ;; filter loop
    (doseq [[k v] filter]
      (some-> query
              (.filter (name k), (get v 0), (get v 1))))
    ;; order loop
    (doseq [[k v] order]
      (some-> query
              (.order (name k) v)))
    ;; limit
    (some-> query
            (.limit limit))))

(defn- ds-data [data]
  (map (fn [[k v]] {:name (name k) :value v}) data))