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
  (jq/html ($ ".rt-list") (html/rts new-val)))

(add-watch data/restaurants 1 rt-watch)

(defn show-modal [] 
  (.modal ($ "#modal") "show"))

(hiccups/defhtml rt-create-body []
  [:div {:class "rt-create"}
    [:div [:span "Name: "][:input {:id "rt-name" :type "text"}]] 
    [:div [:span "Address: "][:input {:id "rt-address" :type "text"}]]])

(defn build-rt-create-modal []
  (jq/html ($ "#modal .modal-content") 
           (hiccups/html 
             (html/mdl-head [:h4 "Create Restaurant"])
             (html/mdl-body (rt-create-body))
             (html/mdl-create-foot)))
  (jq/bind ($ "#modal-create-btn") :click create-click))

(defn add-rt-click [evt]
  (build-rt-create-modal)
  (show-modal))

(defn init []
  (jq/html ($ "#container") (html/rt-display))
  (api/fetch-restaurants fetch-success)
  (jq/bind ($ "#rt-add-btn") :click add-rt-click)
  )

(set! (.-onload js/window) init)

