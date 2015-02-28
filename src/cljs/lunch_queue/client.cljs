(ns lunch-queue.client
  (:use [jayq.core :only [$]])
  (:require-macros [hiccups.core :as hiccups])
  (:require [jayq.core :as jq]
            [lunch-queue.api :as api]
            [lunch-queue.data :as data]
            [lunch-queue.html :as html]
            [hiccups.runtime :as hiccupsrt]
            [lunch-queue.util :as util]))

(defn fetch-success [resp]
  (reset! data/restaurants (util/json->clj resp)))

(defn create-click [evt]
  (let [rt-name (jq/val ($ "#rt-name"))
        rt-address (jq/val ($ "#rt-address"))
        rt-data {:name rt-name :address rt-address}]
    (api/create-restaurant 
      rt-data (fn [res] (api/fetch-restaurants fetch-success)))))

(defn rt-watch [k rests old-val new-val]
  (jq/html ($ ".rt-container") (html/rts new-val)))

(add-watch data/restaurants 1 rt-watch)

(defn init []
  (jq/html ($ "#container") (html/rt-display))
  (api/fetch-restaurants fetch-success)
  (jq/bind ($ "#rt-create-btn") :click create-click))

(set! (.-onload js/window) init)

