(ns lunch-queue.data
  (:require [clojure.tools.logging :as log]))

(def ^{:private true} restaurants (atom []))

(defn get-restaurants [] @restaurants)

(defn create-restaurant [info]
  (swap! restaurants conj info))
