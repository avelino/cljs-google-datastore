(ns cljs-google-datastore.core
  (:require [cljs-google-datastore.maker :as maker]
            ["@google-cloud/datastore" :refer [Datastore]]))

(defn ^Datastore datastore
  [& {:keys [opt]}]
  (new Datastore))

(defn query
  [ds, kind, filter, & {:keys [order, limit]}]
  (.runQuery ds (maker/ds-filter ds kind filter
                             :order order
                             :limit limit)))

(defn save
  [ds, kind, data]
  (let [entity {:key (make-key ds kind)
                :data (make-data data)}]
    (.save ds (clj->js entity))
    entity))
