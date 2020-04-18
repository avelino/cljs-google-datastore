(ns cljs-google-datastore.core
  (:require ["@google-cloud/datastore" :refer [Datastore]]))

(defn ^Datastore datastore
  [& {:keys [opt]}]
  (new Datastore))

(defn- make-key
  [ds, kind]
  (.key ds kind))

(defn- make-filter
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

(defn query
  [ds, kind, filter, & {:keys [order, limit]}]
  (js->clj (.runQuery ds (make-filter ds kind filter
                                      :order order
                                      :limit limit))))

(defn- make-data [data]
  (map (fn [[k v]] {:name (name k) :value v}) data))

(defn save
  [ds, kind, data]
  (let [entity {:key (make-key ds kind)
                :data (make-data data)}]
    (.save ds (clj->js entity))
    entity))
