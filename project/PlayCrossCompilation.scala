import uk.gov.hmrc.playcrosscompilation.{AbstractPlayCrossCompilation, PlayVersion}
import uk.gov.hmrc.playcrosscompilation.PlayVersion.Play25

object PlayCrossCompilation extends AbstractPlayCrossCompilation(defaultPlayVersion = Play25){
  override def playCrossScalaBuilds(scalaVersions: Seq[String]): Seq[String] =
    playVersion match {
      case PlayVersion.Play25 => scalaVersions.filter(version => version.startsWith("2.11"))
      case PlayVersion.Play26 => scalaVersions
      case PlayVersion.Play27 => scalaVersions.filter(version => version.startsWith("2.12"))
    }
}