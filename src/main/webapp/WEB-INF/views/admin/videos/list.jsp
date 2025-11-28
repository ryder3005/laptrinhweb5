<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid py-4">
  <div class="row">
    <div class="col-12">
      <div class="card">
        <div class="card-header d-flex justify-content-between align-items-center">
          <h3 class="mb-0">Quản lý Video</h3>
          <a href="${pageContext.request.contextPath}/admin/videos/create" class="btn btn-primary">
            <i class="fas fa-plus"></i> Thêm mới
          </a>
        </div>
        <div class="card-body">
          <!-- Thông báo thành công -->
          <c:if test="${not empty success}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
              <i class="fas fa-check-circle"></i> ${success}
              <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
          </c:if>

          <!-- Form tìm kiếm -->
          <form method="get" action="${pageContext.request.contextPath}/admin/videos" class="mb-4">
            <div class="row">
              <div class="col-md-10">
                <input type="text" name="q" class="form-control"
                       placeholder="Tìm kiếm theo tiêu đề video..."
                       value="${q}">
              </div>
              <div class="col-md-2">
                <button type="submit" class="btn btn-info w-100">
                  <i class="fas fa-search"></i> Tìm kiếm
                </button>
              </div>
            </div>
          </form>

          <!-- Bảng danh sách -->
          <div class="table-responsive">
            <table class="table table-hover table-bordered">
              <thead class="table-dark">
              <tr>
                <th width="5%">ID</th>
                <th width="12%">Poster</th>
                <th width="25%">Tiêu đề</th>
                <th width="15%">Danh mục</th>
                <th width="10%">Lượt xem</th>
                <th width="10%">Trạng thái</th>
                <th width="15%">Thao tác</th>
              </tr>
              </thead>
              <tbody>
              <c:choose>
                <c:when test="${empty videos}">
                  <tr>
                    <td colspan="7" class="text-center text-muted py-4">
                      <i class="fas fa-video fa-3x mb-3 d-block"></i>
                      Không có dữ liệu
                    </td>
                  </tr>
                </c:when>
                <c:otherwise>
                  <c:forEach var="video" items="${videos}">
                    <tr>
                      <td>${video.id}</td>
                      <td>
                        <c:choose>
                          <c:when test="${not empty video.poster}">
                            <img src="${video.poster}"
                                 class="img-thumbnail"
                                 style="width: 100%; height: 80px; object-fit: cover;"
                                 alt="${video.title}">
                          </c:when>
                          <c:otherwise>
                            <div class="bg-secondary text-white d-flex align-items-center justify-content-center"
                                 style="width: 100%; height: 80px;">
                              <i class="fas fa-video fa-2x"></i>
                            </div>
                          </c:otherwise>
                        </c:choose>
                      </td>
                      <td>
                        <strong>${video.title}</strong>
                        <c:if test="${not empty video.description}">
                          <br>
                          <small class="text-muted">
                              ${video.description.length() > 60 ? video.description.substring(0, 60).concat('...') : video.description}
                          </small>
                        </c:if>
                      </td>
                      <td>
                        <c:if test="${not empty video.category}">
                                                        <span class="badge bg-info">
                                                            <i class="fas fa-folder"></i> ${video.category.categoryName}
                                                        </span>
                        </c:if>
                      </td>
                      <td>
                        <i class="fas fa-eye text-primary"></i>
                        <strong>${video.views}</strong>
                      </td>
                      <td>
                        <c:choose>
                          <c:when test="${video.active}">
                                                            <span class="badge bg-success">
                                                                <i class="fas fa-check"></i> Hoạt động
                                                            </span>
                          </c:when>
                          <c:otherwise>
                                                            <span class="badge bg-secondary">
                                                                <i class="fas fa-times"></i> Ẩn
                                                            </span>
                          </c:otherwise>
                        </c:choose>
                      </td>
                      <td>
                        <a href="${pageContext.request.contextPath}/admin/videos/edit/${video.id}"
                           class="btn btn-sm btn-warning" title="Sửa">
                          <i class="fas fa-edit"></i>
                        </a>
                        <button type="button" class="btn btn-sm btn-danger"
                                onclick="confirmDelete(${video.id})" title="Xóa">
                          <i class="fas fa-trash"></i>
                        </button>
                      </td>
                    </tr>
                  </c:forEach>
                </c:otherwise>
              </c:choose>
              </tbody>
            </table>
          </div>

          <!-- Phân trang -->
          <c:if test="${videoPage.totalPages > 1}">
            <nav aria-label="Page navigation">
              <ul class="pagination justify-content-center">
                <!-- Previous -->
                <li class="page-item ${videoPage.first ? 'disabled' : ''}">
                  <a class="page-link"
                     href="${pageContext.request.contextPath}/admin/videos?page=${videoPage.number - 1}&q=${q}">
                    Trước
                  </a>
                </li>

                <!-- Page numbers -->
                <c:forEach begin="0" end="${videoPage.totalPages - 1}" var="i">
                  <li class="page-item ${videoPage.number == i ? 'active' : ''}">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/admin/videos?page=${i}&q=${q}">
                        ${i + 1}
                    </a>
                  </li>
                </c:forEach>

                <!-- Next -->
                <li class="page-item ${videoPage.last ? 'disabled' : ''}">
                  <a class="page-link"
                     href="${pageContext.request.contextPath}/admin/videos?page=${videoPage.number + 1}&q=${q}">
                    Sau
                  </a>
                </li>
              </ul>
            </nav>

            <p class="text-center text-muted">
              Trang ${videoPage.number + 1} / ${videoPage.totalPages}
              - Tổng số: <strong>${videoPage.totalElements}</strong> video
            </p>
          </c:if>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Form xóa ẩn -->
<form id="deleteForm" method="post" action="${pageContext.request.contextPath}/admin/videos/delete">
  <input type="hidden" name="id" id="deleteId">
</form>

<script>
  function confirmDelete(id) {
    if (confirm('Bạn có chắc chắn muốn xóa video này?\n\nThao tác này không thể hoàn tác!')) {
      document.getElementById('deleteId').value = id;
      document.getElementById('deleteForm').submit();
    }
  }
</script>