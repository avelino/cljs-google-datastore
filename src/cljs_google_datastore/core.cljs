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
    query))

(defn query
  [ds, kind, filter]
  (js->clj (.runQuery ds (make-filter ds kind filter))))

(defn save
  [ds, kind, data]
  (let [entity {:key (make-key ds kind), :data data}]
    (.save ds (clj->js entity))
    entity))
