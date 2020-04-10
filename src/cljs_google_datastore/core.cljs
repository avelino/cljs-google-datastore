(ns cljs-google-datastore.core
  (:require ["@google-cloud/datastore" :refer [Datastore]]))

(defn ^Datastore datastore
  [& {:keys [opt]}]
  (new Datastore))

(defn- make-key
  [ds, kind]
  (.key ds kind))

(defn save
  [ds, kind, data]
  (let [entity {:key (make-key ds kind), :data data}]
    (.save ds (clj->js entity))
    entity))
