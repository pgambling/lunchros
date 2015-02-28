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

(hiccups/defhtml cancel-btn [attrs]
    [:button 
     (merge {:type "button" :class "btn btn-default"} attrs)
     "Cancel"])

;;------------------------------
;; Begin Modal Functions
;;------------------------------

(hiccups/defhtml mdl-close-btn []
    [:button {:type "button" 
              :class "close" 
              :data-dismiss "modal"
              :aria-label "close"}
     [:span {:aria-hidden "true"} "&times;"]])

(hiccups/defhtml mdl-head [contents & more] 
    (conj [:div {:class "modal-header"} (mdl-close-btn)] contents more))

(hiccups/defhtml mdl-foot [contents & more] 
    (conj [:div {:class "modal-footer"}] contents more))

(hiccups/defhtml mdl-body [contents & more]
    (conj [:div {:class "modal-body"}] contents more))


(hiccups/defhtml mdl-cancel-btn []
    (cancel-btn {:data-dismiss "modal"}))

(hiccups/defhtml mdl-create-foot []
    (mdl-foot 
         (mdl-cancel-btn)
         [:button {:type "button" :class "btn btn-primary"} "Create"]))

(hiccups/defhtml modal-test []
    [:div {:class "modal fade" :id "wat"}
     [:div {:class "modal-dialog"}
      [:div {:class "modal-content"}
       (mdl-head [:h4 {:class "modal-title"} "Test Title"])
       (mdl-body [:p "Sexy Time"])
       (mdl-create-foot)
       ]]])
