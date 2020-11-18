<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" rel="stylesheet"/>
<style>

.toast {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  margin: auto;

  max-width: 400px;
  padding: 6px;
  background: #cccccc;
  color: #333333;
  font-family: sans-serif;
  text-align: center;
  border: 1px solid #aaaaaa;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);

  visibility: hidden;
  opacity: 0;
  transition: opacity 0.2s, top 0.2s, visibility 0.2s;
}

.toast--visible {
  top: 20px;
  opacity: 1;
  visibility: visible;
}

.toast--success {
  background: #00c02b;
  border-color: #009d23;
  color: #ffffff;
}

.toast--error {
  background: #d50000;
  border-color: #ba0000;
  color: #ffffff;
}


</style>
</head>
<body>



 <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
<script>
	const Toast = {
			  init() {
			    this.hideTimeout = null;

			    this.el = document.createElement("div");
			    this.el.className = "toast";
			    document.body.appendChild(this.el);
			  },

			  show(message, state) {
			    clearTimeout(this.hideTimeout);

			    this.el.textContent = message;
			    this.el.className = "toast toast--visible";

			    if (state) {
			      this.el.classList.add('toast--'+state);
			    }

			    this.hideTimeout = setTimeout(() => {
			      this.el.classList.remove("toast--visible");
			    }, 3000);
			  }
			};

			document.addEventListener("DOMContentLoaded", () => Toast.init());

			</script>
</body>
</html>