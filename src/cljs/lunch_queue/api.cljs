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

(def ajax-del (merge ajax-defaults {:type "DELETE"}))

(defn- ajax-json [data] 
  {:data (util/stringify (clj->js data)) :dataType "json"})

(defn- get-url [] (.-href (.-location js/window)))

(defn- restaurant-url 
  ([] (str (get-url) "api/restaurants"))
  ([id] (str (restaurant-url) "/" id)))

(defn get-restaurants [id success-fn]
 (jq/ajax (restaurant-url id) (merge ajax-get {:success success-fn})))

(defn fetch-restaurants [success-fn]
 (jq/ajax (restaurant-url) (merge ajax-get {:success success-fn})))

(defn create-restaurant [data success-fn]
  (jq/ajax (restaurant-url) 
           (merge ajax-post {:success success-fn } (ajax-json data))))

(defn delete-restaurant [id success-fn]
 (jq/ajax (restaurant-url id) (merge ajax-del {:success success-fn})))
