(ns cljs-google-datastore.core
  (:require [cljs-google-datastore.maker :as maker]
            ["@google-cloud/datastore" :refer [Datastore]]))

(defn ^Datastore datastore
  [& {:keys [opt]}]
  (new Datastore))

(defn query
  [ds, kind, filter, & {:keys [order, limit, group]}]
  (.runQuery ds (maker/ds-filter ds kind filter
                             :order order
                             :limit limit
                             :group group)))

(defn save
  [ds, kind, data, & {:keys [key]}]
  (.save ds (clj->js {:key (maker/ds-key ds kind :key key)
                      :data (maker/ds-data data)})))

(defn delete
  [ds, kind, key]
  (.delete ds (maker/ds-key ds kind :key key)))