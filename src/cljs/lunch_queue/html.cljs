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
  [:button.btn.btn-default (assoc attrs :type "button") "Cancel"])

;;-------------------------------------
;; Begin Restaurant Component Functions
;;-------------------------------------
(hiccups/defhtml rt-table-row [rt]
  [:tr [:td (:name rt)] [:td (:address rt)] 
    [:td [:button.close.remove-rt-btn {:type "button" :aria-label "close" }
      [:span {:aria-hidden "true"} "&times;"]]]])

(hiccups/defhtml rts [rts] 
  (map rt-table-row rts))

(hiccups/defhtml rt-table []
  [:table#rt-table.table
    [:thead [:tr [:th "Name"][:th "Address"] [:td]]]
    [:tbody.rt-list]])

(hiccups/defhtml rt-add-btn []
  [:button#rt-add-btn.btn.btn-primary {:type "button"} 
    "Add Restaurant"])

(hiccups/defhtml rt-display []
  [:div.rt-container.row 
    (rt-table) 
    [:div.rt-action (rt-add-btn)]
    (mdl-placeholder)])

;;------------------------------
;; Begin Modal Functions
;;------------------------------

(hiccups/defhtml mdl-close-btn []               
  [:button.close {:type "button" 
                    :data-dismiss "modal"
                    :aria-label "close"}
     [:span {:aria-hidden "true"} "&times;"]])

(hiccups/defhtml mdl-head [& contents] 
  [:div.modal-header (mdl-close-btn) contents])

(hiccups/defhtml mdl-foot [& contents] 
  [:div.modal-footer contents])

(hiccups/defhtml mdl-body [& contents]
  [:div.modal-body contents])

(hiccups/defhtml mdl-cancel-btn []
  (cancel-btn {:data-dismiss "modal"}))

(hiccups/defhtml mdl-create-foot []
  (mdl-foot 
    (mdl-cancel-btn)
    [:button#modal-create-btn.btn.btn-primary 
      {:type "button" 
       :data-dismiss "modal"}
      "Create"]))

(hiccups/defhtml mdl-placeholder []
    [:div {:class "modal fade" :id "modal"}
     [:div {:class "modal-dialog"}
      [:div {:class "modal-content"}]]])
