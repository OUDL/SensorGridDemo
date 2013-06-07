(ns dotty.draw
  (:use [dotty.state :only [grid-sensors tick]])
  (:require [quil.core :as qc]
            [dotty.draw-sensors :as draw-sensors]))

(defn draw
  []
  (swap! tick inc)
  (qc/background 0 0 0)
  (draw-sensors/draw-sensor-grid grid-sensors tick))
