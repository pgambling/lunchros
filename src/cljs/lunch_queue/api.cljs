(ns lunch-queue.api
  (:require [jayq.core :as jq]
            [clojure.string :as string]
            [lunch-queue.util :as util]))

(def ajax-defaults
  {:accepts "application/json"
   :contentType "application/json; charset=UTF-8"
   :dataType "json"
   :traditional true
   :cache false})

(defn- ajax-json [data] 
  (util/stringify (clj->js data)))

(defn- get-url [] (.-href (.-location js/window)))

(def restaurant-url "api/restaurants")

(defn get-restaurant [id success-fn]
 (jq/ajax (str restaurant-url "/" id) 
          (assoc ajax-defaults :type "GET"
                               :success success-fn)))

(defn get-restaurants [success-fn]
 (jq/ajax restaurant-url
          (assoc ajax-defaults :type "GET"
                               :success success-fn)))

(defn create-restaurant [data success-fn]
  (jq/ajax (restaurant-url) 
           (assoc ajax-defaults :type "POST"
                                :success success-fn
                                :data (ajax-json data))))

(defn delete-restaurant [id success-fn]
 (jq/ajax (restaurant-url id) 
          (assoc :type "DELETE"
                 :success success-fn)))
