(ns lunch-queue.util)

(defn log [data]
  (.log js/console data))

(defn json->clj
  "Convert a JSON string or JS object to a native ClojureScript data structure"
  [data]
  (let [data (if (string? data) (.parse js/JSON data) data)]
    (-> data
        (js->clj :keywordize-keys true))))

(defn stringify [data]
  (.stringify js/JSON data))
