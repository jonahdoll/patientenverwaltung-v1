# Definiert die fehlende Kubernetes PriorityClass "medium-priority"
# die von deinem Helm Chart referenziert wird.

resource "kubernetes_priority_class" "medium_pc" {
  metadata {
    # Der Name muss EXAKT dem Namen im Helm Chart entsprechen.
    name = "medium-priority"
  }

  # Der Wert bestimmt die Priorität. 500000 ist ein mittlerer Wert.
  # Höhere Zahlen haben eine höhere Priorität.
  value = 500000

  global_default = false
  description    = "Mittlere Priorität für Standard-Anwendungen, von Helm Chart verwendet."
}
