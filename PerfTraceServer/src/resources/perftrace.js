var request = new XMLHttpRequest();

// Open a new connection, using the GET request on the URL endpoint
request.open('GET', 'http://localhost:8080/', true);

request.onload = function () {
      console.log(this.response);
  // Begin accessing JSON data here
//  var data = JSON.parse(this.response);
//
//  if (request.status >= 200 && request.status < 400) {
//    data.forEach(movie => {
//      console.log(this.response);
//    });
//  } else {
//    console.log('error');
//  }
//  }
}

// Send request
request.send();