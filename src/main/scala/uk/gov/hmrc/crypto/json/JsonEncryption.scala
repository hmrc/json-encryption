/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.crypto.json

import play.api.libs.json._
import uk.gov.hmrc.crypto.{Crypted, PlainText, CompositeSymmetricCrypto, Protected}

class JsonEncryptor[T]()(implicit crypto: CompositeSymmetricCrypto, wrts: Writes[T]) extends Writes[Protected[T]] {
  override def writes(o: Protected[T]): JsValue = storeInJson(o)

  private def storeInJson(protectd: Protected[T]): JsValue = {
    val pt = PlainText(wrts.writes(protectd.decryptedValue).toString())
    JsString(crypto.encrypt(pt).value)
  }

}

class JsonDecryptor[T](implicit crypto: CompositeSymmetricCrypto, rds: Reads[T]) extends Reads[Protected[T]] {
  override def reads(json: JsValue): JsResult[Protected[T]] = {
    val crypted = Crypted(json.as[String])
    JsSuccess(readFromJson(crypted))
  }

  private def readFromJson(encrypted: Crypted): Protected[T] = {
    val plainText = crypto.decrypt(encrypted)
    val obj = Json.parse(plainText.value).as[T]
    Protected(obj)
  }

}
