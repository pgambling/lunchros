(ns lunch-queue.data
  (:require [clojure.tools.logging :as log]))

(def ^{:private true} restaurants (atom []))

(defn- next-rt-id []
  (inc (apply max (conj (map :id @restaurants) 0))))

(defn delete-restaurant [id]
  (reset! restaurants (vec (filter #(not= :id id) @restaurants))))

(defn get-restaurant [id]
  (first (filter #(= :id id) @restaurants)))

(defn get-restaurants [] @restaurants)

(defn create-restaurant [info]
  (let [rt (assoc info :id (next-rt-id))]
    (swap! restaurants conj rt)))
