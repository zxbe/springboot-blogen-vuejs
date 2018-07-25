import axios from 'axios'
import constants from './common/constants'

/**
 * example of creating a custom axios instance that we can configure and then import into our modules where needed
 *
 * Axios docs at {@link https://github.com/axios/axios}
 * @type {AxiosInstance}
 */

const instance = axios.create({
  // TODO see if authentication header can be configured with Bearer token
  baseURL: constants.API_SERVER_URL,
  responseType: 'json' // default
  // `headers` are custom headers to be sent
  // headers: {'X-Requested-With': 'XMLHttpRequest'},
})

// NOTE Authorization header is set in Login component
// alter defaults after instance creation instance.defaults.headers.common['SOMETHING'] = 'something';

export default instance
