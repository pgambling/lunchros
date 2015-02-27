(ns lunch-queue.data
  (:require [jayq.core :as jq]
            [clojure.string :as string]
            [lunch-queue.util :as util]))


(def restaurants (atom []))
