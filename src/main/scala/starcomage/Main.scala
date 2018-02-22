package starcomage


trait Monoid[A] {
  def nextTurn(a: A): A
}

object Monoid {
  implicit object ResourceProducerMonoid extends Monoid[ResourceProducer] {
    def nextTurn(startingProducer: ResourceProducer) {
      val updatedResourceCount = startingProducer.resourceCount + startingProducer.producerCount
      new ResourceProducer(
        startingProducer.name,
        startingProducer.producerCount,
        updatedResourceCount
      )
    }
  }

  implicit object FactionMonoid extends Monoid[Faction] {
    def nextTurn(startingFaction: Faction) {
      new Faction(
        nextTurn(startingFaction.army),
        nextTurn(startingFaction.mines),
        nextTurn(startingFaction.magic),
        startingFaction.fortress,
      )
    }
  }
}

class ResourceProducer(nameConstructor: String, producerCountConstructor: Int, resourceCountConstructor: Int) {
  val name: String = nameConstructor
  val producerCount: Int = producerCountConstructor
  val resourceCount: Int = resourceCountConstructor
}

class Faction(armyConstructor: ResourceProducer, minesConstructor: ResourceProducer, magicConstructor: ResourceProducer, fortressConstructor: Fortress) {
  val army: ResourceProducer = armyConstructor
  val mines: ResourceProducer = minesConstructor
  val magic: ResourceProducer = magicConstructor
  val fortress: Fortress = fortressConstructor
}

object TutorialApp {
  def main(args: Array[String]): Unit = {
    println("Hello world!")
  }
}
