(ns lunch-queue.html
  (:use [jayq.core :only [$]])
  (:require-macros [hiccups.core :as hiccups])
  (:require [jayq.core :as jq]
            [hiccups.runtime :as hiccupsrt]
            [lunch-queue.util :as util]))

(declare mdl-placeholder)

;;------------------------
;; Begin Utility Functions
;;------------------------
(hiccups/defhtml cancel-btn [attrs]
    [:button 
     (merge {:type "button" :class "btn btn-default"} attrs)
     "Cancel"])

;;-------------------------------------
;; Begin Restaurant Component Functions
;;-------------------------------------
(hiccups/defhtml rt-table-row [rt]
    [:tr [:td (:name rt)] [:td (:address rt)] 
     [:td [:button {:type "button" :class "close" :aria-label "close" }
           [:span {:aria-hidden "true"} "&times;"]]]])

(hiccups/defhtml rts [rts] 
  (map rt-table-row rts))

(hiccups/defhtml rt-table []
  [:table {:class "table" :id "rt-table"}
   [:thead [:tr [:th "Name"][:th "Address"] [:td]]]
   [:tbody {:class "rt-list"}]])

(hiccups/defhtml rt-add-btn []
    [:button 
     {:type "button" :id "rt-add-btn" :class "btn btn-primary"} 
     "Add Restaurant"])

(hiccups/defhtml rt-display []
  [:div {:class "rt-container row"} 
   (rt-table) [:div {:class "rt-action"} (rt-add-btn)]
   (mdl-placeholder)])

;;------------------------------
;; Begin Modal Functions
;;------------------------------

(hiccups/defhtml mdl-close-btn []
    [:button {:type "button" 
              :class "close" 
              :data-dismiss "modal"
              :aria-label "close"}
     [:span {:aria-hidden "true"} "&times;"]])

(hiccups/defhtml mdl-head [& contents] 
    (conj [:div {:class "modal-header"} (mdl-close-btn)] contents))

(hiccups/defhtml mdl-foot [& contents] 
    (conj [:div {:class "modal-footer"}] contents))

(hiccups/defhtml mdl-body [& contents]
    (conj [:div {:class "modal-body"}] contents))

(hiccups/defhtml mdl-cancel-btn []
    (cancel-btn {:data-dismiss "modal"}))

(hiccups/defhtml mdl-create-foot []
    (mdl-foot 
         (mdl-cancel-btn)
         [:button {:type "button" 
                   :id "modal-create-btn"
                   :class "btn btn-primary"
                   :data-dismiss "modal"} 
          "Create"]))

(hiccups/defhtml mdl-placeholder []
    [:div {:class "modal fade" :id "modal"}
     [:div {:class "modal-dialog"}
      [:div {:class "modal-content"}]]])
