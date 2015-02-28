(ns lunch-queue.html
  (:use [jayq.core :only [$]])
  (:require-macros [hiccups.core :as hiccups])
  (:require [jayq.core :as jq]
            [hiccups.runtime :as hiccupsrt]
            [lunch-queue.util :as util]))

(hiccups/defhtml rts [rts]
  (map (fn [rt] 
         [:div {:class "rt-info"}
          [:div {:class "rt-attr rt-name"} 
           [:span "Name: "][:span (:name rt)]]
          [:div {:class "rt-attr rt-address"} 
           [:span "Address: "][:span (:address rt)]]])
       rts))

(hiccups/defhtml rt-list []
  [:div {:class "rt-list"}])

(hiccups/defhtml rt-create-btn []
  [:div {:class "rt-create"}
   [:div [:span "Name: "][:input {:id "rt-name" :type "text"}]]
   [:div [:span "Address: "][:input {:id "rt-address" :type "text"}]]
   [:input {:type "button" :value "Add Restaurant" :id "rt-create-btn"}]])

(hiccups/defhtml rt-display []
  [:div {:class "rt-container"} 
   (rt-list) (rt-create-btn)])

