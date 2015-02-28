(ns lunch-queue.html
  (:use [jayq.core :only [$]])
  (:require-macros [hiccups.core :as hiccups])
  (:require [jayq.core :as jq]
            [hiccups.runtime :as hiccupsrt]
            [lunch-queue.util :as util]))

(hiccups/defhtml rts [rts]
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

