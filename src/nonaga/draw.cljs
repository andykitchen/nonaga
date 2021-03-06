(ns nonaga.draw
  (:use [nonaga.react :only [div p svg create-class render-component]]
        [nonaga.draw.instructions       :only [instructions]]
        [nonaga.draw.valid-marble-moves :only [draw-valid-marble-moves]]
        [nonaga.draw.marbles            :only [draw-marbles]]
        [nonaga.draw.rings              :only [draw-rings]]
        [nonaga.draw.potential-rings    :only [draw-potential-rings]]
        [nonaga.draw.last-ring          :only [draw-last-ring]])
  (:require [nonaga.core :as n]))

; To implement win detection it would be quite convenient to have the game
; state in an atom. This would allow for a win detection watcher that would
; spot that someone has one and would change the :event to end the game.

(defn react-watcher [component]
  (fn [key ref old-state new-state]
    (.setState component #js {:wrapper ref})))

(def board
  (create-class
    "getInitialState"
    (fn []
      (this-as this
               (let [state-atom
                      (atom (-> n/initial-game
                            (assoc :event [:turn-began :red])))]
                 (add-watch state-atom :react (react-watcher this))
                 #js {:wrapper state-atom})))
    "render"
    (fn []
      (this-as this
               (let [state @(.-wrapper (.-state this)) ]
                 (div {}
                      (p {:id "instructions"}
                         (instructions (:event state)))
                      (svg {:width 480 :height 480}
                           (draw-rings this state)
                           (draw-potential-rings this state)
                           (draw-last-ring state)
                           (draw-marbles this state :red)
                           (draw-marbles this state :blue)
                           (draw-valid-marble-moves this state))))))))

(def main-board (board))

(defn start []
  (render-component main-board (js/document.getElementById "content")))

