<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <script type="text/javascript" src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<body>
  <div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">
    <div class="inner-cardbox">
      <div class="row mb-3">
        <div class="col-lg-12">
          <div class="row">
            <div class="col-lg-12">
              <form action="${contextPath}/mst/fillterbook" class="row" id="submitForm" method="POST">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <div class="col-md-12">
                  <div class="row">
                    <div class="form-group col-md-3">
                      <label>Category</label>
                      <select class="form-control form-control-sm" id="categoryName" name="categoryId" required>
                        <option value="">Select</option>
                        <c:forEach items="${categoryList}" var="categorylist">
                          <option value="${categorylist.categoryId}" ${categorylist.categoryId eq fillterDto.categoryId ? 'selected' : '' }>${categorylist.categoryName}</option>
                        </c:forEach>
                      </select>
                    </div>
                    <div class="form-group col-md-4">
                      <label>Search :</label>
                      <input type="text" name="books" id="books" class="form-control form-control-sm autocomplete-box" placeholder="Search for Books" multiple="multiple" />
                      <button id="voice-search-button" onclick="spechStart()">
                        <i class='bx bx-microphone'></i>
                      </button>
                    </div>
                    <div class="form-group col-md-3">
                      <label>Language :</label>
                      <select class="form-control form-control-sm " id="libId" name="languageId">
                        <option value="">--Select--</option>
                        <c:forEach items="${languageList}" var="language">
                          <option value="${language.valueId}">${language.valueCode}</option>
                        </c:forEach>
                      </select>
                    </div>
                    <div class="col-md-2" style="margin-top:20px">
                      <button type="button" class="btn btn-primary filter-btn" onclick="fillter();" value="Submit">filter</button>
                    </div>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <hr class="mt-4"/>
      <h6 class="d-flex justify-content-between align-items-center">Trending Books <ul class="slick-arrow list-unstyled mx-0 my-0 py-0 px-0">
          <li class="book-f-prev me-1">
            <i class='bx bx-left-arrow-alt'></i>
          </li>
          <li class="book-f-next">
            <i class='bx bx-right-arrow-alt'></i>
          </li>
        </ul>
      </h6>
      <div class="row">
        <div class="col-lg-12">
          <div class="book-f-slider">
            <c:forEach items="${NEW_BOOK}" var="book">
              <div class="book-box">
                <div class="book-cell">
                  <div class="book-img">
                    <img src="${contextPath}/image/bookcat?bookfilePath=${book.imagePath}" alt="" class="img-fluid book-photo">
                  </div>
                  <div class="book-content">
                    <div class="book-title">${book.bookTitles}</div>
                    <div class="book-author">By ${book.bookTitles}</div>
                    <div class="rate">
                      <ul class="rating yellow">
                        <li>
                          <i class="bx bxs-star"></i>
                        </li>
                        <li>
                          <i class="bx bxs-star"></i>
                        </li>
                        <li>
                          <i class="bx bxs-star"></i>
                        </li>
                        <li>
                          <i class="bx bxs-star-half"></i>
                        </li>
                        <li>
                          <i class="bx bx-star"></i>
                        </li>
                      </ul>
                      <span class="book-voters">1.987k Readers</span>
                    </div>
                    <div class="book-sum">${book.description}</div>
                    <div class="book-see book-yellow">
                      <a href="${contextPath}/mst/reservedBook?catalogCode=${book.bookCatalogCode}" type="button" class="badge">Reserve</a>
                    </div>
                  </div>
                </div>
              </div>
            </c:forEach>
          </div>
        </div>
      </div>
     
      <h6 class="mt-4">Most Popular Books</h6>
       <hr />
      <div class="row mb-3">
        <c:forEach items="${MOST_POPOLER_BOOK}" var="book">
          <div class="col-lg-2">
            <div class="book">
              <div class="book-cover cover1">
                <img src="${contextPath}/image/bookcat?bookfilePath=${book.imagePath}">
                <div class="effect"></div>
                <div class="light"></div>
              </div>
              <div class="book-inside"></div>
            </div>
            <ul class="book-dec">
              <li class="bookName">
              	<h4>${book.bookTitles}</h4>
              </li>
              <li>
                <a href="${contextPath}/mst/reservedBook?catalogCode=${book.bookCatalogCode}" class="badge">Reserve</a>
              </li>
            </ul>
          </div>
        </c:forEach>
      </div>
      <c:forEach items="${bookCatalogsKey}" var="bookCatalogsKey">
        <h6 style="margin-bottom:5px">${bookCatalogsKey.key}</h6>
        <hr style="margin-top:0">
        <div class="row">
          <c:forEach items="${bookCatalogsKey.value}" var="book">
            <div class="col-lg-2">
              <div class="book">
                <div class="book-cover cover1">
                  <img src="${contextPath}/image/bookcat?bookfilePath=${book.imgPath}">
                  <div class="effect"></div>
                  <div class="light"></div>
                </div>
                <div class="book-inside"></div>
              </div>
              <ul class="book-dec">
                <li class="bookName">${book.bookTitles}</li>
              <li><strong>Auther:</strong> RamLal</li>
              <li><strong>Language:</strong> Odia</li>
                <li>
                  <a href="${contextPath}/mst/reservedBook?catalogCode=${book.bookCatalogCode}" class="badge">Reserve</a>
                </li>
              </ul>
            </div>
          </c:forEach>
        </div>
         <div class="col-lg-2 mb-4">
                <!-- Other book information... -->
                <div class="book-see book-yellow"><a href="#" onclick="viewimageProducttotal('${bookCatalogsKey.key}')" type="button" class="badge bg-success">View All</a></div>
            </div>
      </c:forEach>
    </div>
  </div>
  <form action="${contextPath}/mst/fillterbook" class="row" id="fromsubmit" method="POST">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" name="categoryId" id="hdnsubcatId" />
    <input type="hidden" name="subcategoryId" id="hdncatId" />
    <input type="hidden" name="type" id="hdTypeId" />
    <input type="hidden" name="catName" id="hdncatname" />
  </form>
  
<%--   <form action="${contextPath}/mst/fillterbook" class="row" id="fromsubmit" method="POST"> --%>
<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>


<!-- </form>		 -->
</body>
<script>
  function fillter() {
    $("#submitForm").submit();
  }
  /*  	function viewimageProducttotal(subcatagoriesId){
  		console.log(subcatagoriesId);
  		var catlogId = $("#categoryName").val();
  		$("#hdncatId").val(subcatagoriesId);
  		$("#hdTypeId").val("book");
  	 $("#fromsubmit").submit(); 
  		
  	} */
  $(document).ready(function() {
        $("#books").autocomplete({
          serviceUrl: '${contextPath}/findbybookname.htm',
          paramName: "books",
          delimiter: ",",
          width: '195px',
          async: false,
          transformResult: function(response) {
            if ($.parseJSON(response).length == 0) {
              $("#hidden").removeClass("hidden");
              resetValue();
            }
            return {
              suggestions: $.map($.parseJSON(response), function(item) {
                return {
                  value: item.books,
                  code: item.bookcode,
                  image: item.image
                };
              })
            };
          },
          onSelect: function(suggestion) {
            rackshelf(suggestion.code);
          }
        })
          });
</script>
<script>
  let speech = false;

  function spechStart() {
    if (speech) {
      return; // If speech is already in progress, do nothing
    }
    speech = true;
    const recognition = new(window.SpeechRecognition || window.webkitSpeechRecognition)();
    const words = document.querySelector('.words');
    recognition.interimResults = true;
    recognition.addEventListener('result', e => {
      const transcript = Array.from(e.results).map(result => result[0]).map(result => result.transcript).join('');
      document.getElementById("books").value = transcript;
      console.log(transcript);
    });
    recognition.addEventListener('end', () => {
      speech = false;
      //  spechStart(); // Restart recognition when it ends
    });
    recognition.start();
  }
  
  function viewimageProducttotal(catagoriesname){
		$("#hdncatname").val(catagoriesname);
	 $("#fromsubmit").submit(); 
		
	}
</script>
</html>