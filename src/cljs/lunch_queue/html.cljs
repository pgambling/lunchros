(ns lunch-queue.html
  (:use [jayq.core :only [$]])
  (:require-macros [hiccups.core :as hiccups])
  (:require [jayq.core :as jq]
            [hiccups.runtime :as hiccupsrt]
            [lunch-queue.util :as util]))

(hiccups/defhtml rts [rts] 
  (map (fn [rt] [:tr [:td (:name rt)] [:td (:address rt)]]) rts))

(hiccups/defhtml rt-table []
  [:table {:class "table"}
   [:thead [:tr [:th "Name"][:th "Address"]]]
   [:tbody {:class "rt-list"}]])

(hiccups/defhtml rt-create-btn []
  [:div {:class "rt-create"}
   [:div [:span "Name: "][:input {:id "rt-name" :type "text"}]]
   [:div [:span "Address: "][:input {:id "rt-address" :type "text"}]]
   [:button 
    {:type "button" :id "rt-create-btn" :class "btn btn-success"} 
    "Add Restaurant"]])

(hiccups/defhtml rt-display []
  [:div {:class "rt-container row"} 
   [:div {:class "col-md-6"}
   (rt-table) (rt-create-btn)]])

