(ns lunch-queue.client
  (:use [jayq.core :only [$]])
  (:require-macros [hiccups.core :as hiccups])
  (:require [jayq.core :as jq]
            [lunch-queue.api :as api]
            [lunch-queue.data :as data]
            [hiccups.runtime :as hiccupsrt]
            [lunch-queue.util :as util]))

(hiccups/defhtml build-restaurants [rts]
  (map (fn [rt] 
         (util/log rt)
         [:div {:class "rt"}
          [:div [:span "Name: "][:span (:name rt)]]
          [:div [:span "Address: "][:span (:address rt)]]])
       rts))

(hiccups/defhtml rt-container []
  [:div {:class "rt-container"}])

(defn fetch-success [resp]
  (reset! data/restaurants (util/json->clj resp)))

(defn rt-watch [k rests old-val new-val]
  (jq/html ($ ".rt-container") (build-restaurants new-val)))

(add-watch data/restaurants 1 rt-watch)

(defn init []
  (api/fetch-restaurants fetch-success)
  (jq/html ($ "#container") (rt-container)))

(set! (.-onload js/window) init)

