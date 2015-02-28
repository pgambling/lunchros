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
    (rt-table) (rt-create-btn)
    [:button
     {:type "button" :id "test-button" :class "btn"}
     "Test"]
    ]
   (modal-test)
   ])

(hiccups/defhtml mdl-close-btn []
    [:button {:type "button" 
              :class "close" 
              :data-dismiss "modal"
              :aria-label "close"}
     [:span {:aria-hidden "true"} "&times;"]])

(hiccups/defhtml mdl-head [contents] 
    [:div {:class "modal-header"} contents ])

(hiccups/defhtml mdl-foot [contents] 
    [:div {:class "modal-footer"} contents ])

(hiccups/defhtml mdl-body [contents]
    [:div {:class "modal-body"} contents])

(hiccups/defhtml modal-test []
    [:div {:class "modal fade" :id "wat"}
     [:div {:class "modal-dialog"}
      [:div {:class "modal-content"}
       [:div {:class "modal-header"}
        (mdl-close-btn)
        [:h4 {:class "modal-title"} "Test Title"]]
       [:div {:class "modal-body"}
        [:p "Sexy Time"]]
       [:div {:class "modal-footer"}
        [:button 
         {:type "button" 
          :class "btn btn-primary" 
          :data-dismiss "modal"}
         "Coolio"]]]]])


