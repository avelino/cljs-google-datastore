(ns cljs-google-datastore.core
  (:require ["@google-cloud/datastore" :refer [Datastore]]))

(defn- make-key
  [kind]
  (.key datastore kind))

(defn save
  [kind, data]
  (let [entity {:key (make-key kind), :data data}]
    (.save datastore (clj->js entity))
    entity))
