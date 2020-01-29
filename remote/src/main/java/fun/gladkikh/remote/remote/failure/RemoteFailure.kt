package `fun`.gladkikh.remote.remote.failure

import `fun`.gladkikh.common.domain.type.Failure
import `fun`.gladkikh.remote.remote.core.ErrorDescriptionEntity

class ServerError(exception: Throwable):Failure()
class NetworkConnectionError:Failure()
class ServerErrorByDescription(val remote: ErrorDescriptionEntity):Failure()