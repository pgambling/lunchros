(ns lunch-queue.client
  (:use [jayq.core :only [$]])
  (:require-macros [hiccups.core :as hiccups])
  (:require [jayq.core :as jq]
            [lunch-queue.api :as api]
            [lunch-queue.data :as data]
            [hiccups.runtime :as hiccupsrt]
            [lunch-queue.util :as util]))

(defn create-click [evt]
  (let [rt-name (jq/val ($ "#rt-name"))
        rt-address (jq/val ($ "#rt-address"))
        rt-data {:name rt-name :address rt-address}]
    (util/log "hello!")
    (api/create-restaurant rt-data nil)))

(hiccups/defhtml build-restaurants [rts]
  (map (fn [rt] 
         [:div {:class "rt"}
          [:div [:span "Name: "][:span (:name rt)]]
          [:div [:span "Address: "][:span (:address rt)]]])
       rts))

(hiccups/defhtml rt-container []
  [:div {:class "rt-container"}])

(hiccups/defhtml rt-create []
  [:div {:class "rt-create"}
   [:div [:span "Name: "][:input {:id "rt-name" :type "text"}]]
   [:div [:span "Address: "][:input {:id "rt-address" :type "text"}]]
   [:input {:type "button" :value "Create!" :id "rt-create-btn"}]])

(hiccups/defhtml rt-display []
  [:div (rt-container) (rt-create)])

(defn fetch-success [resp]
  (reset! data/restaurants (util/json->clj resp)))

(defn rt-watch [k rests old-val new-val]
  (jq/html ($ ".rt-container") (build-restaurants new-val)))

(add-watch data/restaurants 1 rt-watch)

(defn init []
  (jq/html ($ "#container") (rt-display))
  (api/fetch-restaurants fetch-success)
  (jq/bind ($ "#rt-create-btn") :click create-click))

(set! (.-onload js/window) init)

