(ns dotty.draw-sensors
  (:require [quil.core :as qc])
  (:use [dotty.setup :only [CW HEIGHT NCOLS NROWS RH WIDTH]]))

(def SENSOR_LINE_WIDTH 5.0)

(defn draw-sensor-point
  [t [[col row] { :keys [x y] }]]
  (let [t (* t 0.1)
        n (qc/noise col row t)
        r (+ 1 (* n 4))]
    (qc/fill 255 255)
    (qc/ellipse x y r r)))

(defn update-sensor-point
  [tick-atom new-grid [[col row :as col-row] { :keys [x y] :as sensor }]]
  (let [t (* 0.01 @tick-atom)
        t (+ (rand 0.01) t) ;; Make a randomness of points little smoother.
        pos (+ col (* NCOLS row))
        new-x (+ (* (- NCOLS col 1) CW) (* CW (qc/noise pos t)))
        new-y (+ (* row RH) (* RH (qc/noise pos (+ 5.2 t))))
        new-sensor (-> (assoc sensor :x new-x)
                       (assoc :y new-y))]
    (assoc new-grid col-row new-sensor)))

(defn draw-sensor-grid
  [grid-sensors-atom tick-atom]

  (dorun
   (map (partial draw-sensor-point @tick-atom) @grid-sensors-atom))
  (let [new-grid (doall
                  (reduce (partial update-sensor-point tick-atom) {} @grid-sensors-atom))]
    (reset! grid-sensors-atom new-grid)))
