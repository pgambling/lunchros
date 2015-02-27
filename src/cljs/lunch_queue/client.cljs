(ns lunch-queue.client
  (:use [jayq.core :only [$]])
  (:require [jayq.core :as jq]
            [lunch-queue.data :as data]))


(defn init []
  (data/fetch-restaurants)
  (jq/html ($ "#container") "Testing"))

(set! (.-onload js/window) init)
