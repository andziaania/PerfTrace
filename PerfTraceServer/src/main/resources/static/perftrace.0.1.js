var OUR_STATISCICS_SERVER = 'http://localhost:8080/registerAjaxRequest';

function informAboutAjaxRequest(ajaxRequestUrl) {
    var req = new XMLHttpRequest();
    var params = '?webappId=' + 1 + '&ajaxRequestUrl=' + ajaxRequestUrl
    req.open("GET", "http://localhost:8080/registerAjaxRequest" + params);
    req.send();
}

(function(open) {
    
    function isNotOurStatisticRequest(url) {
        return !url.includes(OUR_STATISCICS_SERVER);
    }

    XMLHttpRequest.prototype.open = function(method, url, async, user, pass) {
    
        this.addEventListener("readystatechange", function() {
            if (this.readyState == 4 && isNotOurStatisticRequest(this.responseURL)) {
                informAboutAjaxRequest(this.responseURL);
            }
            console.log(this.readyState); // this one I changed
        }, false);
        
        open.apply(this, [].slice.call(arguments))
        // open.call(this, method, url, async, user, pass);
    };

})(XMLHttpRequest.prototype.open);