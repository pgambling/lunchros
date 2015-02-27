(ns lunch-queue.data
  (:require [jayq.core :as jq]
            [clojure.string :as string]
            [lunch-queue.util :as util]))


(def ajax-defaults
  {:accepts "application/json"
   :contentType "application/json; charset=UTF-8"
   :traditional true
   :cache false})

(def ajax-get (merge ajax-defaults {:type "GET"}))

(defn- get-url [] (.-href (.-location js/window)))

(defn- restaurant-url [] (str (get-url) "api/restaurants"))

(defn fetch-restaurants []
  (jq/ajax (restaurant-url) ajax-get))
