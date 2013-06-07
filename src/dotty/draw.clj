(ns dotty.draw
  (:use [dotty.state :only [grid-sensors tick]])
  (:require [quil.core :as qc]
            [dotty.draw-sensors :as draw-sensors]))

(defn draw
  []
  (swap! tick inc)
  (qc/background 0 0 0)
  (draw-sensors/draw-sensor-grid grid-sensors tick)
  ;; (qc/display-filter :blur 2)
  
  ;; (qc/translate (* 0.5 (qc/width)) (* 0.5 (qc/height)))
  ;; (let [theta (* 0.02 @tick)
  ;;       x (* 0.5 (qc/width) (Math/sin theta))]
  ;;   (qc/fill 255 40)
  ;; (qc/ellipse x 0 200 200))
  )
