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

(defn refresh-restaurants []
  (api/get-restaurants fetch-success))

(defn create-click [evt]
  (let [rt-name (jq/val ($ "#rt-name"))
        rt-address (jq/val ($ "#rt-address"))
        rt-data {:name rt-name :address rt-address}]
    (api/create-restaurant rt-data refresh-restaurants)))

(defn rt-watch [_ _ _ new-val]
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

(defn remove-rt-click [evt]
  (let [tr (.-currentTarget evt)
        row-idx (.-rowIndex tr)
        rt-id (:id (@data/restaurants (dec row-idx)))]
    (api/delete-restaurant rt-id refresh-restaurants)))

(defn init []
  (jq/html ($ "#container") (html/rt-display))
  (refresh-restaurants)
  (jq/bind ($ "#rt-add-btn") :click add-rt-click)
  (jq/on ($ "#rt-table") :click ".remove-rt-btn" remove-rt-click))

(jq/document-ready init)
