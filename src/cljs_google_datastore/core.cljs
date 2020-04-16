(ns cljs-google-datastore.core
  (:require ["@google-cloud/datastore" :refer [Datastore]]))

(defn ^Datastore datastore
  [& {:keys [opt]}]
  (new Datastore))

(defn- make-key
  [ds, kind]
  (.key ds kind))

(defn- make-filter
  [ds, kind, filter, & {:keys [param-order, param-limit]}]
  (let [query (.createQuery ds kind)]
    (if-not (empty? param-order)
      (reset-meta! query
                   (.order query param-order)))
    (if-not (empty? param-limit)
      (reset-meta! query
                   (.limit query param-limit)))
    query))

(defn query
  [ds, kind, filter, & {:keys [param-order, param-limit]}]
  (js->clj (.runQuery ds (make-filter ds kind filter
                                      :order param-order
                                      :limit param-limit))))

(defn save
  [ds, kind, data]
  (let [entity {:key (make-key ds kind), :data data}]
    (.save ds (clj->js entity))
    entity))
