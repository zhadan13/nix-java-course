<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Blog</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<header>
    <div th:fragment="header" xmlns:th="https://www.w3.org/1999/xhtml">
        <div class="container">
            <div class="blog-header py-3">
                <nav class="container d-flex align-items-center flex-column flex-md-row justify-content-between">
                    <div class="col-4 pt-1"></div>
                    <div class="col-4 text-center">
                        <a class="blog-header-logo text-dark" href="/news">
                            <h3><b>
                                <ins>BlogBaster</ins>
                            </b></h3>
                        </a>
                    </div>
                    <div class="col-4 d-flex justify-content-end align-items-center">
                        <a class="p-2 text-dark" href="#" aria-label="Search">
                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none"
                                 stroke="currentColor"
                                 stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="mx-3"
                                 viewBox="0 0 24 24"><title>Search</title>
                                <circle cx="10.5" cy="10.5" r="7.5"></circle>
                                <path d="M21 21l-5.2-5.2"></path>
                            </svg>
                        </a>
                        <a class="btn btn-sm btn-outline-secondary" href="/login">Sign in</a>
                    </div>
                </nav>
            </div>
            <div class="site-header sticky-top py-1">
                <nav class="container d-flex align-items-center flex-column flex-md-row justify-content-between">
                    <a class="p-2 text-dark" href="/news" aria-label="BlogBaster">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="none" stroke="currentColor"
                             stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="d-block mx-auto"
                             viewBox="0 0 25 25"><title>The BlogBaster</title>
                            <circle cx="12" cy="12" r="10"></circle>
                            <path d="M14.31 8l5.74 9.94M9.69 8h11.48M7.38 12l5.74-9.94M9.69 16L3.95 6.06M14.31 16H2.83m13.79-4l-5.74 9.94"></path>
                        </svg>
                    </a>
                    <a class="p-2 text-dark" href="/news">Home</a>
                    <a class="p-2 text-dark" href="/news/blog">Blog</a>
                    <a class="p-2 text-dark" href="/news/blog/add">Add article</a>
                    <a class="p-2 text-dark" href="/about">About</a>
                </nav>
            </div>
        </div>
    </div>
</header>

<div class="container mt-5 mb-5">
    <h1>Add new article</h1><br>
    <form action="addPostServlet" method="post">
        <label>
            <input type="text" name="title" placeholder="Enter article title" class="form-control">
        </label><br>
        <label>
            <input type="text" name="date" placeholder="Enter anons" class="form-control">
        </label><br>
        <label>
            <textarea name="text" placeholder="Enter article text" class="form-control"></textarea>
        </label><br>
        <button type="submit" class="btn btn-success" th:href="blog.jsp">Add article</button>
    </form>
</div>

<footer>
    <div th:fragment="footer" xmlns:th="https://www.w3.org/1999/xhtml">
        <footer class="text-muted py-5">
            <div class="container">
                <a class="blog-header-logo text-dark" href="#">
                    <b>
                        <ins>Back to top</ins>
                    </b>
                </a>
                <p class="mb-1">The BlogBaster © 2021. All rights reserved.</p>
            </div>
        </footer>
    </div>
</footer>
</body>
</html>