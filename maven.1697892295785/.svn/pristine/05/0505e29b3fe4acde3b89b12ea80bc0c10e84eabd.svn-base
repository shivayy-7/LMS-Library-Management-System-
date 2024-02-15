<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script src="${contextPath}/assets/appJs/framework/data-table.js" type="text/javascript"></script>

<div class="content">
    <div class="panel-header bg-primary-gradient">
        <div class="page-inner">
            <div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
                <div>
                    <h2 class="text-white fw-bold m-0">Member View</h2>
                </div>
                <div class="ml-md-auto">
                    <a href="${contextPath}" class="btn btn-white btn-border btn-round mr-2"><i class="fa fa-home"></i></a>
                    <a href="javascript:void(0)" class="btn btn-secondary btn-round">/ Member View </a>
                </div>
            </div>
        </div>
    </div>
    <div class="page-inner dashboard-page">
        <div class="row ">
            <div class="col-md-12">
                <div class="card ">
                    <div class="card-header">
                        <div class="panel-actions">
                            <a href="#" class="fa fa-caret-down"></a>
                        </div>
                        <h4 class="card-title">Member View</h4>
                    </div>
                    <div class="card-body">
                        <div class="col-md-12 mt-4">
                            <input type="file" id="fileInput" accept="image/*">
                            <button onclick="scanQRCode()">Scan</button>
                            <canvas id="canvas" style="display:none;"></canvas>
                            <div id="output"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
function scanQRCode() {
    const fileInput = document.getElementById('fileInput');
    const canvas = document.getElementById('canvas');
    const output = document.getElementById('output');
    const ctx = canvas.getContext('2d');
    
    const file = fileInput.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function() {
            const img = new Image();
            img.onload = function() {
                canvas.width = img.width;
                canvas.height = img.height;
                ctx.drawImage(img, 0, 0, img.width, img.height);
                const imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
                const code = jsQR(imageData.data, imageData.width, imageData.height);
                if (code) {
                    output.innerHTML = 'QR Code Detected! Data: ' + code.data;
                } else {
                    output.innerHTML = 'No QR Code found';
                }
            }
            img.src = reader.result;
        }
        reader.readAsDataURL(file);
    } else {
        output.innerHTML = 'Please select an image file';
    }
}
</script>
