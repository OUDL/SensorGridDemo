(ns dotty.setup
  (:use [dotty.state :only [grid-sensors]])
  (:require [quil.core :as qc]))

(def WIDTH 1280.0)
(def HEIGHT 1024.0)

(def NCOLS 24)
(def NROWS 18)

(def CW (/ WIDTH NCOLS))
(def RH (/ HEIGHT NROWS))

(defn setup
  []
  ;; Initialize grid sensor state:
  (dorun
   (for [col (range NCOLS)
         row (range NROWS)
         :let [x (+ (* col CW) (rand-int CW))
               y (+ (* row RH) (rand-int RH))]]
     (swap! grid-sensors #(assoc % [col row] {:x x :y y})))))
