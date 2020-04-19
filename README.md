# Google Cloud Datastore
Interoperability `@google-cloud/datastore` to cljs (_ClojureScript_)

[![Clojars Project](https://img.shields.io/clojars/v/cljs-google-datastore.svg)](https://clojars.org/cljs-google-datastore)

## Installation

Add package dependencie in `project.clj`:

``` clojure
(defproject hello-world "1.0.0-SNAPSHOT"
  :description "FIXME: write"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [cljs-google-datastore "1.0.0"]])
```

Download dependencies:

``` shell
lein deps
```

## Example

``` clojure
(ns core
  (:require [cljs-google-datastore.core :as datastore]))

(defn- main []
  (let [ds (datastore/datastore)
        data {:created (.toJSON (new js/Date))
              :name "Google Cloud Datastore by cljs"
              :url "https://github.com/avelino/cljs-google-datastore"}])

    ;; save data
    (-> (datastore/save ds "KEY-NAME" data)
        (.then (fn [r] (prn r))))

    ;; update data - key 123
    (-> (datastore/save ds "KEY-NAME" data :key 123)
        (.then (fn [r] (prn r))))

    ;; delete - key 123
    (-> (datastore/delete ds "KEY-NAME" 123)
        (.then (fn [r] (prn r))))
    
    ;; get records -- apply filters
    (-> (datastore/query ds
                         "KEY-NAME"
                         {:created [[">" (.toJSON (new js/Date "2020-04-03T00:00:00z"))]
                                    ["<" (.toJSON (new js/Date "2020-04-09T00:00:00z"))]]
                          :active true}
                         :group ["created"]
                         :order {:created {:descending false}}
                         :limit 10)
        (.then (fn [r] (println r)))))
```
