(defproject cljs-google-datastore "1.0.10"
  :url "https://github.com/avelino/cljs-google-datastore"
  :description "Google Cloud Datastore client to ClojureScript"
  :license {:name "MIT"
            :url "http://opensource.org/licenses/MIT"}
  :scm {:name "git" :url "https://github.com/avelino/cljs-google-datastore"}

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]]
  :npm-deps {"@google-cloud/datastore" "5.1.0"}

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-codox "0.10.7"]]

  :codox {:language :clojurescript
          :source-paths ["src"]
          :doc-paths []}

  :source-paths ["src"])
