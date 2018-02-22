package starcomage.game

import scala.concurrent.{ExecutionContext}

import akka.actor.{ActorSystem, ActorRef, Actor}



final case class Tower(height: Int)
final case class Wall(height: Int)
final class Fortress private(private val internal: ActorRef)



object Fortress {
  def newFortress(
    startingTowerHeight: Int,
    startingWallHeight: Int,
    system: ActorSystem
  ): Fortress = {
    val actor = system.actorOf(Props(
      new FortressActor(
        startingTowerHeight: Int,
        startingWallHeight: Int,)
    ))
    new Fortress(actor)
  }
}


class FortressActor(startingTowerHeight: Int, startingWallHeight: Int) extends Actor {
  private implicit val ec: ExecutionContext = context.dispatcher
  var tower = new Tower(startingTowerHeight)
  var wall = new Wall(startingWallHeight)
}