(ns lunch-queue.api
  (:require [jayq.core :as jq]
            [clojure.string :as string]
            [lunch-queue.util :as util]))

(def ajax-defaults
  {:accepts "application/json"
   :contentType "application/json; charset=UTF-8"
   :traditional true
   :cache false})

(def ajax-get (merge ajax-defaults {:type "GET"}))

(def ajax-post (merge ajax-defaults {:type "POST"}))

(defn- get-url [] (.-href (.-location js/window)))

(defn- restaurant-url [] (str (get-url) "api/restaurants"))

(defn fetch-restaurants [success-fn]
 (jq/ajax (restaurant-url) (merge ajax-get {:success success-fn})))

(defn create-restaurant [data success-fn]
  (jq/ajax (restaurant-url) 
           (merge ajax-post {:success success-fn 
                             :data (util/stringify (clj->js data))
                             :dataType "json"
                             })))
