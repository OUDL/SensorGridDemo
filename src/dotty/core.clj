(ns dotty.core
  (:use [dotty.setup :only [HEIGHT WIDTH]])
  (:require [dotty.draw :as dynamic-draw]
            [dotty.setup :as dynamic-setup]
            [quil.core :as qc]))

(defn on-close-sketch []
  ;;(stop)
  )

(defn run-sketch []
  (qc/defsketch the-sketch
    :title "Dotty"
    :setup dynamic-setup/setup
    :draw dynamic-draw/draw
    :on-close on-close-sketch
    :size [WIDTH HEIGHT]))

(defn stop-sketch [] (qc/sketch-stop the-sketch))
(defn restart-sketch [] (qc/sketch-start the-sketch))
(defn close-sketch [] (qc/sketch-close the-sketch))

;;(run-sketch)
;;(qc/sketch-stop the-sketch)
;;(qc/sketch-start the-sketch)
;;(qc/sketch-close the-sketch)

