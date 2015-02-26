(ns lunch-queue.data
  (:require [clojure.tools.logging :as log]))

(def ^{:private true} restaurants (atom []))

(defn get-restaurants [] @restaurants)

(defn create-restaurant [info]
  (log/info @restaurants)
  (log/info info)
  (log/info (conj @restaurants info))
  (swap! restaurants conj info))
