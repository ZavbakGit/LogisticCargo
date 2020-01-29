package `fun`.gladkikh.remote.remote


import `fun`.gladkikh.common.domain.type.Either
import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.remote.remote.entity.RequestEntity
import `fun`.gladkikh.remote.remote.entity.ResponseEntity


interface RequestRemote {
    fun request(user:String,password:String,request: String): Either<Failure, String>
}