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
        data [{:name "created",
               :value (.toJSON (new js/Date))}
              {:name "name",
               :value "Google Cloud Datastore by cljs"}
              {:name "url",
               :value "https://github.com/avelino/cljs-google-datastore"}]]

    ;; save data
    (datastore/save ds "KEY-NAME" data)
    
    ;; get all records
    (-> (datastore/query ds
                         "KEY-NAME"
                         {:created [">" (.toJSON (new js/Date "2020-04-03T00:00:00z"))]}
                         :order {:created {:descending false}}
                         :limit 10)
        (.then (fn [r] (println r))))))
```
