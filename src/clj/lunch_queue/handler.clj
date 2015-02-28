(ns lunch-queue.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as json-mw]
            [lunch-queue.data :as data]
            [clojure.tools.logging :as log])
  (:use [hiccup.core]))

(defroutes api-routes
    (GET "/users" [] { :body { :key "value"} })
    (GET "/restaurants" [] { :body (data/get-restaurants)} )
    (POST "/restaurants" {body :body}
          (data/create-restaurant (select-keys body [:name :address]))
          {:body (data/get-restaurants)}))

(defn- page-wrapper [& content]
  (html
    [:head
     [:meta {:http-equiv "Content-type" 
             :content "text/html; charset=utf-8"}]
     [:link {:rel "stylesheet" 
             :type "text/css" 
             :href "bootstrap/css/bootstrap.min.css"}]
     [:link {:rel "stylesheet" 
             :type "text/css" 
             :href "css/style.css"}]
     [:title "Lunchros"]]
    [:body content]))

(defn- index-page []
  (page-wrapper
    [:div {:id "container"} [:h2 "Lunchros"]]
    [:script {:src "/js/jquery-2.1.3.min.js"}]
    [:script {:src "bootstrap/js/bootstrap.min.js"}]
    [:script {:src "/js/cljs.js"}]))

(defroutes app-routes
  (GET "/" [] (index-page))
  (-> (context "/api" [] api-routes)
      (json-mw/wrap-json-body {:keywords? true} )
      (json-mw/wrap-json-response))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app app-routes)
