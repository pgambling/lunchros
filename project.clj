(defproject lunch-queue "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [ring/ring-json "0.3.1"]
                 [hiccup "1.0.4"]
                 ; begin clojure script dependencies
                 [org.clojure/clojurescript "0.0-2913"]
                 [jayq "2.4.0"]
                 [hiccups "0.3.0"]]

  :plugins [[lein-ring "0.8.13"]
            [lein-cljsbuild "1.0.5"]]

  :source-paths ["src/clj"]

  :cljsbuild
  {
   :builds
   {
    :main
    {
     :source-path "src/cljs",
     :compiler    {
                   :output-to     "resources/public/js/cljs.js"
                   :optimizations :simple
                   :pretty-print  true
                   }
     }
    }
   }

  :ring {:handler lunch-queue.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
